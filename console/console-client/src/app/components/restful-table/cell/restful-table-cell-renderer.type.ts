import {RestfulTableColumn} from "../types/restful-table-column.type";

export interface RestfulTableCellRenderer {
  column: RestfulTableColumn;
  row: any;
}
