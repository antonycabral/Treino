import { Exercicio } from "./Exercicio";

export interface Treino {
    id: number;
    nome: string;
    diaSemana: string;
    objetivo: string;
    exercicios: Exercicio[];
    dataCriacao: Date;
    ultimaExecucao?: Date;
}