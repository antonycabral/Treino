import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TreinoService } from '../../service/treino.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-treino-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule,
    HttpClientModule
  ],
  providers: [TreinoService],
  templateUrl: './treino-form.component.html',
  styleUrl: './treino-form.component.css'
})
export class TreinoFormComponent implements OnInit {
  treinoForm: FormGroup;
  isEditing = false;
  
  constructor(
    private fb: FormBuilder,
    private treinoService: TreinoService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.treinoForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      nivel: ['', Validators.required],
      usuarioId: ['']
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEditing = true;
      this.carregarTreino(id);
    }
  }

  onSubmit() {
    if (this.treinoForm.valid) {
      if (this.isEditing) {
        this.treinoService.atualizarTreino(this.route.snapshot.params['id'], this.treinoForm.value)
          .subscribe(() => this.router.navigate(['/home']));
      } else {
        this.treinoService.criarTreino(this.treinoForm.value)
          .subscribe(() => this.router.navigate(['/home']));
      }
    }
  }

  private carregarTreino(id: string) {
    this.treinoService.getTreino(id).subscribe(treino => {
      this.treinoForm.patchValue(treino);
    });
  }
}
