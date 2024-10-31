export interface Atividade {
    id: number;
    data: Date;
    descricao: string;
    tipo: 'treino' | 'avaliacao' | 'outro';
    treinoId?: number;
}