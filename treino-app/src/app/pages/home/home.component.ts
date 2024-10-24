import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommunicationService } from '../../services/communication.service';



@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HttpClientModule, CommonModule,FormsModule],
  providers:[UserService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  users: User[] = [];

    constructor(
        private userService: UserService,
        private communicationService: CommunicationService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.userService.getUsers().subscribe(
            (data: User[]) => {
                this.users = data;
            },
            error => {
                console.error('Erro ao carregar usuários', error);
            }
        );
    }

    selectUser(userId: number): void {
        this.communicationService.changeUserId(userId);
        this.router.navigate(['/user', userId]);
    }
}
