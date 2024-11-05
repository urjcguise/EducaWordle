import { Wordle } from './wordle';

export class Contest {
    contestName!: string;
    competitionId!: number;
    startDate!: Date;
    endDate!: Date;
    useDictionary!: boolean;
    useExternalFile!: boolean;
    fileRoute!: string;
    wordles!: Wordle[];

    constructor(contestName: string,
        startDate: Date,
        endDate: Date,
        useDictionary: boolean,
        useExternalFile: boolean,
        fileRoute: string,
        competitionId: number,
        wordles: Wordle[] = []) {
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useDictionary = useDictionary;
        this.useExternalFile = useExternalFile;
        this.fileRoute = fileRoute;
        this.competitionId = competitionId;
        this.wordles = wordles;
    }
}