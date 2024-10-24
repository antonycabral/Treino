export interface Exercicio {
    id: number;
    nome: string;
    carga: number;
    series: number;
    tempo: number; // em segundos
    descanso: number; // em segundos
    repeticoes: number; // nova propriedade
    treinoId: number; // ID do treino ao qual este exercício pertence
}