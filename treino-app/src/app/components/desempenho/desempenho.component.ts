import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DesempenhoService } from '../../service/desempenho.service';
import { Chart, registerables } from 'chart.js';



@Component({
  selector: 'app-desempenho',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h3 class="h5 mb-0">Seu Desempenho</h3>
        </div>
        <div class="card-body">
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <h4>Total de Treinos</h4>
                            <p class="display-4">{{frequenciaTotal}}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <h4>Média Semanal</h4>
                            <p class="display-4">{{mediaTreinos | number:'1.1-1'}}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <h4>Treino Favorito</h4>
                            <p class="display-4">{{treinoMaisFrequente}}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Treinos na Semana</h4>
                        </div>
                        <div class="card-body">
                            <canvas #weeklyChart></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Treinos no Mês</h4>
                        </div>
                        <div class="card-body">
                            <canvas #monthlyChart></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  `
})
export class DesempenhoComponent implements OnInit, AfterViewInit {
  @Input() usuario: any;
  @ViewChild('weeklyChart') weeklyChart!: ElementRef;
  @ViewChild('monthlyChart') monthlyChart!: ElementRef;

  frequenciaTotal: number = 0;
  mediaTreinos: number = 0;
  treinoMaisFrequente: string = '';
  treinosSemanais: number[] = [];
  treinosMensais: number[] = [];

  constructor(private desempenhoService: DesempenhoService) {
    Chart.register(...registerables);
  }

  ngOnInit() {
    if (this.usuario?.id) {
      this.carregarDesempenho(this.usuario.id);
    }
  }

  ngAfterViewInit() {
    if (this.usuario?.id) {
      this.carregarDesempenho(this.usuario.id);
    }
  }

  private carregarDesempenho(userId: string) {
    this.desempenhoService.getDesempenhoUsuario(userId).subscribe({
      next: (data) => {
        this.frequenciaTotal = data.frequenciaTotal;
        this.mediaTreinos = data.mediaTreinos;
        this.treinoMaisFrequente = data.treinoMaisFrequente;
        this.treinosSemanais = data.treinosSemanais;
        this.treinosMensais = data.treinosMensais;
        
        this.renderizarGraficos();
      },
      error: (error) => console.error('Erro ao carregar desempenho:', error)
    });
  }

  private renderizarGraficos() {
    this.renderizarGraficoSemanal();
    this.renderizarGraficoMensal();
  }

  private renderizarGraficoSemanal() {
    if (this.weeklyChart) {
      const ctx = this.weeklyChart.nativeElement.getContext('2d');
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
          datasets: [{
            label: 'Treinos por dia',
            data: this.treinosSemanais,
            backgroundColor: '#007bff',
            borderRadius: 5
          }]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              ticks: { stepSize: 1 }
            }
          }
        }
      });
    }
  }

  private renderizarGraficoMensal() {
    if (this.monthlyChart) {
      const ctx = this.monthlyChart.nativeElement.getContext('2d');
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: Array.from({length: this.treinosMensais.length}, (_, i) => i + 1),
          datasets: [{
            label: 'Treinos no mês',
            data: this.treinosMensais,
            borderColor: '#28a745',
            tension: 0.1,
            fill: true
          }]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              ticks: { stepSize: 1 }
            }
          }
        }
      });
    }
  }
}