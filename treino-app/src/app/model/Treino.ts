import { Exercicio } from "./Exercicio";

export interface Treino {
    nome: string; // Nome do treino
    exercicios: Exercicio[]; // Lista de exercícios
}