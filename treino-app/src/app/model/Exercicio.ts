export interface Exercicio {
    id: number;
    nome: string;
    series: number;
    repeticoes: number;
    peso: number;
    concluido: boolean;
    observacoes?: string;
}