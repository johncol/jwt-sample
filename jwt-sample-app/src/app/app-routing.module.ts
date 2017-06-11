import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateRequestComponent } from "app/create-request/create-request.component";
import { UpdateRequestComponent } from "app/update-request/update-request.component";
import { RequestListComponent } from "app/request-list/request-list.component";

const routes: Routes = [
  { path: '', redirectTo: '/create-request', pathMatch: 'full' },
  { path: 'create-request', component: CreateRequestComponent },
  { path: 'update-request', component: UpdateRequestComponent },
  { path: 'request-list', component: RequestListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
