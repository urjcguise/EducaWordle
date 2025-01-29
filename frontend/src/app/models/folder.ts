import { User } from "./user";
import { Wordle } from "./wordle";

export class Folder {
    id!: number;
    name!: string;
    professor!: User;
    wordles!: Wordle[];
    folders!: Folder[];
    parentFolder!: Folder;
}