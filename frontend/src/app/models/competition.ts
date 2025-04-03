import { Contest } from "./contest";

export class Competition {
    competitionName!: string;
    id!: number;
    contests!: Contest[];

    constructor(competitionName: string) {
        this.competitionName = competitionName;
    }
}