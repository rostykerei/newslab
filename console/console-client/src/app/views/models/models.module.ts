import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {PublishersComponent} from "./publishers.component";
import {RestfulTableModule} from "../../components/restful-table/restful-table.module";
import {PublishersTableActionsRenderer} from "./publishers-table-actions-renderer.component";

@NgModule({
  declarations: [PublishersComponent, PublishersTableActionsRenderer],
  imports     : [BrowserModule, RestfulTableModule],
  exports     : [PublishersComponent, PublishersTableActionsRenderer],
  entryComponents: [PublishersTableActionsRenderer]
})

export class ModelsModule {}
