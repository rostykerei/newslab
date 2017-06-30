import {Type} from "@angular/core";
import {RestfulTableCellRenderer} from "../cell/restful-table-cell-renderer.type";

export interface RestfulTableColumn {
  id: string,
  title?: string,
  sortable?: boolean,
  renderer?: Type<RestfulTableCellRenderer>
}
