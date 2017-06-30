import {NgModule} from "@angular/core";
import {RestfulTableComponent} from "./restful-table.component";
import {BrowserModule} from "@angular/platform-browser";
import {RestfulTableCellDirective} from "./cell/restful-table-cell.directive";
import {RestfulTableCellComponent} from "./cell/restful-table-cell.component";
import {RestfulTableDefaultCellRenderer} from "./cell/restful-table-default-cell-renderer.component";

@NgModule({
  declarations: [RestfulTableComponent, RestfulTableCellDirective, RestfulTableCellComponent, RestfulTableDefaultCellRenderer],
  imports     : [BrowserModule],
  exports     : [RestfulTableComponent, RestfulTableCellComponent],
  entryComponents: [RestfulTableDefaultCellRenderer]
})
export class RestfulTableModule {

}
