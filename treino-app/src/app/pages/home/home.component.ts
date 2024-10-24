import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { UserService } from '../../services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {  Router } from '@angular/router';


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
  searchName: string = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    }, error => {
      console.error('Erro ao carregar usuários:', error);
    });
  }

  onSearchUser(): void {
    if (this.searchName.trim() === '') {
      this.loadUsers(); // Reload all users if searchName is empty
    } else {
      this.userService.getUsersByName(this.searchName).subscribe(users => {
        this.users = users;
      }, error => {
        console.error('Erro ao buscar usuários:', error);
      });
    }
  }

  selectUser(userId: number): void {
    this.router.navigate([`/profile`, userId]);
  }
}
