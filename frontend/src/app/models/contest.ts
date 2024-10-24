export class Contest {
    contestName!: string;
    competitionId!: number;
    startDate!: Date;
    endDate!: Date;

    constructor(contestName: string, startDate: Date, endDate: Date) {
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}