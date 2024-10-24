import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {
  private userIdSource = new BehaviorSubject<number | null>(null);
  currentUserId = this.userIdSource.asObservable();

  changeUserId(userId: number): void {
      this.userIdSource.next(userId);
  }
}
