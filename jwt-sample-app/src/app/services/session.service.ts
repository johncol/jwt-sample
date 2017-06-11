import { Injectable } from '@angular/core';

import { Request } from './../model/request';

@Injectable()
export class SessionService {
  private request: Request;

  saveRequest(request: Request): void {
    this.request = request;
  }

  id(): number {
    return this.request.id;
  }

  identification(): number {
    return this.request ? this.request.identification : null;
  }

  done(): boolean {
    return this.request ? this.request.done : false;
  }

  inSession(): boolean {
    return !!this.request;
  }

}
