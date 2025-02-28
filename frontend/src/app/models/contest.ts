import { Competition } from './competition';

export class Contest {
    id!: number;
    contestName!: string;
    competition!: Competition;
    startDate!: Date;
    endDate!: Date;
    numTries!: number;
    useDictionary!: boolean;
    useExternalFile!: boolean;
    fileRoute!: string;
    wordlesLength!: number[];

    constructor(contestName: string,
        startDate: Date,
        endDate: Date,
        numTries: number,
        useDictionary: boolean,
        useExternalFile: boolean,
        fileRoute: string,
        competition: Competition,
        wordlesLength: number[]) {
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numTries = numTries;
        this.useDictionary = useDictionary;
        this.useExternalFile = useExternalFile;
        this.fileRoute = fileRoute;
        this.competition = competition;
        this.wordlesLength = wordlesLength;
    }
}