import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs';

import { Request } from './../model/request';
import { CreateRequestResponse } from './responses/create-request-response';

@Injectable()
export class RequestService {
  api: string = 'http://localhost:8080';
  resource: string = '/request';

  constructor(private http: Http) { }

  all(): Observable<Request[]> {
    return this.http
      .get(this.api + this.resource)
      .map(response => response.json())
      .do(response => console.log('List requests http response: ', response));
  }

  create(request: Request): Observable<CreateRequestResponse> {
    return this.http
      .post(this.api + this.resource, request)
      .map(response => response.json())
      .do(response => console.log('Create request http response: ', response));
  }

  update(requestId: number): Observable<any> {
    return this.http
      .put(this.api + this.resource + `/${requestId}`, {})
      .do(response => console.log('Request update http response: ', response));
  }

}
