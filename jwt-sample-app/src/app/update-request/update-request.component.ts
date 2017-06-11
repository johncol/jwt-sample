import { Component, OnInit } from '@angular/core';

import { Request } from './../model/request';
import { RequestService } from './../services/request.service';
import { SessionService } from './../services/session.service';

@Component({
  selector: 'jwtapp-update-request',
  templateUrl: './update-request.component.html',
  styleUrls: ['./update-request.component.css']
})
export class UpdateRequestComponent implements OnInit {
  inSession: boolean;
  success: boolean;
  httpError: boolean;
  
  constructor(
    private requestService: RequestService,
    private sessionService: SessionService
  ) { }

  ngOnInit() {
    this.inSession = this.sessionService.inSession();
    this.success = this.sessionService.done();
  }

  updateRequest(): void {
    this.requestService.update(this.sessionService.id()).subscribe(
      response => {
        this.success = true;
        this.httpError = false;
      },
      error => {
        this.success = false;
        this.httpError = true;
      }
    );
  }

}
