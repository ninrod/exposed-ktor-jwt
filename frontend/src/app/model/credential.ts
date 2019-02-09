export class Credential {
  public username: string
  public password: string
  constructor(values: object = {}) {
    Object.assign(this, values);
  }
}
