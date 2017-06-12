export class CreateRequestResponse {
  constructor(
    public request: number, 
    public success: boolean,
    public jwt: { token: string }
  ) { }
}
