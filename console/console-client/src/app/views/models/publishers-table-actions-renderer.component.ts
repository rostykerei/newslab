import {Component, Input} from "@angular/core";
import {RestfulTableColumn} from "../../components/restful-table/types/restful-table-column.type";
import {RestfulTableCellRenderer} from "../../components/restful-table/cell/restful-table-cell-renderer.type";

@Component({
  template: `<a (click)="click()" >action</a>`
})
export class PublishersTableActionsRenderer implements RestfulTableCellRenderer {
  @Input() column: RestfulTableColumn;
  @Input() row: any;

  click() : void {
    alert(this.row.id);
  }
}
