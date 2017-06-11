import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateRequestComponent } from './create-request/create-request.component';
import { UpdateRequestComponent } from './update-request/update-request.component';
import { RequestListComponent } from './request-list/request-list.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RequestService } from './services/request.service';
import { SessionService } from './services/session.service';

@NgModule({
  declarations: [
    AppComponent,
    CreateRequestComponent,
    UpdateRequestComponent,
    RequestListComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [RequestService, SessionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
