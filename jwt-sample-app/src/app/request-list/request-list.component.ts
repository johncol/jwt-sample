import { Component, OnInit } from '@angular/core';

import { RequestService } from './../services/request.service';
import { Request } from './../model/request';

@Component({
  selector: 'jwtapp-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {
  requests: Request[];

  constructor(
    private requestService: RequestService
  ) { }

  ngOnInit() {
    this.requestService.all().subscribe(
      requests => this.requests = requests,
      error => this.requests = []
    );
  }

}
