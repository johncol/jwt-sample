import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { RequestService } from "app/services/request.service";
import { SessionService } from "app/services/session.service";

import { Request } from './../model/request';
import { CreateRequestResponse } from "app/services/responses/create-request-response";

@Component({
  selector: 'jwtapp-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.css']
})
export class CreateRequestComponent implements OnInit {
  form: FormGroup;
  success: boolean = false;
  httpError: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private requestService: RequestService,
    protected sessionService: SessionService
  ) { }

  ngOnInit() {
    this.success = this.sessionService.inSession();
    this.form = this.formBuilder.group({
      identification: [this.sessionService.identification(), Validators.required]
    });
  }

  create(): void {
    if (this.form.valid) {
      let request: Request = new Request(this.form.value.identification, 'captcha-value');
      console.log('request', request);
      this.requestService.create(request).subscribe(
        (response: CreateRequestResponse) => {
          request.id = response.request;
          this.sessionService.saveRequest(request);
          this.success = response.success;
          this.httpError = false;
        },
        error => this.httpError = true);
    }
  }

}
