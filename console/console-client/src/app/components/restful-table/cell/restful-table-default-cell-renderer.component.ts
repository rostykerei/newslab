import {Component, Input} from "@angular/core";
import {RestfulTableColumn} from "../types/restful-table-column.type";
import {RestfulTableCellRenderer} from "./restful-table-cell-renderer.type";

@Component({
  template: `{{row[column.id]}}`
})
export class RestfulTableDefaultCellRenderer implements RestfulTableCellRenderer {
  @Input() column: RestfulTableColumn;
  @Input() row: any;
}
