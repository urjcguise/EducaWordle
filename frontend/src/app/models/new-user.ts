export class NewUser {
    name!: string;
    email!: string;
    password!: string;
    authorities!: string[];

    constructor(name: string, email: string, password: string) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
