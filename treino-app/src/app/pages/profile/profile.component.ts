import { Component } from '@angular/core';
import { User } from '../../model/User';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { BtnLinkComponent } from "../../shared/btn-link/btn-link.component";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterModule, BtnLinkComponent],
  providers: [UserService],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  user?: User;

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    const userId = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getUser(userId).subscribe(user => this.user = user);
  }

}
