export class User {
  public login: string
  public firstname: string
  public lastname: string
  public description: string

  constructor(values: object = {}) {
    Object.assign(this, values);
  }
}
