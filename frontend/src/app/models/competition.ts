import { Contest } from "./contest";
import { User } from "./user";

export class Competition {
    competitionName!: string;
    id!: number;
    contests!: Contest[];
    professor!: User;

    constructor(competitionName: string) {
        this.competitionName = competitionName;
    }
}