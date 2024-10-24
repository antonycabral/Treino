import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-btn-link',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './btn-link.component.html',
  styleUrl: './btn-link.component.css'
})
export class BtnLinkComponent {

  @Input()
  route:String = '';
  
  @Input()
  texto:String = '';
}
