export class Credential {
  public name: string
  public password: string
  constructor(values: object = {}) {
    Object.assign(this, values)
  }
}
