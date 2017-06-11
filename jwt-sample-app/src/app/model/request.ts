export class Request {
  id: number;
  done: boolean;
  constructor(
    public identification: number,
    public captcha: string
  ) { }
}
