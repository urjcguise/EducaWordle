export class Contest {
    contestName!: string;
    competitionId!: number;

    constructor(contestName: string, competitionId: number) {
        this.contestName = contestName;
        this.competitionId = competitionId;
    }
}