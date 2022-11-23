export class User {
  constructor(
    public userId: number,
    public firstName: string,
    public lastName: string,
    public phoneNumber?: string,
    public email?: string
  ) {}
}
