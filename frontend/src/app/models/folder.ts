import { User } from "./user";
import { Wordle } from "./wordle";

export class Folder {
    name!: string;
    professor!: User;
    wordles!: Wordle[];
    folders!: Folder[];
}