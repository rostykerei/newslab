import {NgModule} from "@angular/core";
import {RestfulTableComponent} from "./restful-table.component";
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [RestfulTableComponent],
  imports     : [BrowserModule],
  exports     : [RestfulTableComponent],
})
export class RestfulTableModule {

}
