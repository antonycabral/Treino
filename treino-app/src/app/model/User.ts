import { Treino } from "./Treino";

export interface User {
    id: number;
    nomeCompleto: string;
    sexo: string;
    idade: number;
    peso: number;
    treinos?: Treino[];
}