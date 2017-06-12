import { Injectable } from '@angular/core';
import { Headers, RequestOptionsArgs } from '@angular/http';

@Injectable()
export class AuthService {
  private _token: string = null;
  private bearer: string = 'Bearer ';

  get token(): string {
    return this.bearer + this._token;
  }

  set token(token: string) {
    this._token = token;
  }

  hasToken(): boolean {
    return !!this._token;
  }

  authorizationHeaders(): Headers {
    return new Headers({ Authorization: this.token });
  }

  authorizationRequestOptions(): RequestOptionsArgs {
    return {
      headers: new Headers({ Authorization: this.token })
    };
  }

}
