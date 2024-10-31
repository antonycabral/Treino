import { Atividade } from "./atividade";

export interface Desempenho {
    treinosRealizados: number;
    frequenciaSemanal: number;
    ultimasAtividades: Atividade[];
    metasSemana: {
        previsto: number;
        realizado: number;
    }
}