export class Token {
  public token: string
  constructor(values: object = {}) {
    Object.assign(this, values)
  }
}
