import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {PublishersComponent} from "./publishers.component";
import {RestfulTableModule} from "../../components/restful-table/restful-table.module";

@NgModule({
  declarations: [PublishersComponent],
  imports     : [BrowserModule, RestfulTableModule],
  exports     : [PublishersComponent],
})

export class ModelsModule {}
