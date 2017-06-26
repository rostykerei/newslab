import {Component, Input, OnInit} from "@angular/core";
import {TableDataService} from "./table-data.service";
import {TableData} from "./table-data";
import {Column} from "./column";

@Component({
  selector: 'nl-restful-table',
  providers: [TableDataService],
  templateUrl: 'restful-table.template.html'
})
export class RestfulTableComponent implements OnInit {

  init: boolean = false;
  loading: boolean = false;

  @Input() title: String;
  @Input() columns: Column[] = [];

  rows: string[][] = [];

  rowsPerPage: number = 25;

  ROWS_PER_PAGE_MENU: number[] = [10, 25, 50, 100, 200];

  constructor(private tableDataService: TableDataService) {

  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loading = true;

    this.tableDataService.getData().then(
      data => this.dataLoaded(data)
    );
  }

  dataLoaded(data:TableData): void {
    this.tableData = data;
    this.init = true;
    this.loading = false;
  }

  set tableData(tableData: TableData) {
    for (let column of this.columns) {
      column.sortDir = tableData.sortedByColumn == column.id ? tableData.sortingDirection : null;
    }

    this.rows = [];

    for (let dataRow of tableData.data) {
      let row: string[] = [];
      let i = 0;

      for (let column of this.columns) {
        row[i] = dataRow[column.id];
        i++;
      }

      this.rows.push(row);
    }
  }

  sort(column: Column): void {
    if (column.sortable) {
      this.loadData();
    }
  }

  refresh(e: Event): void {
    e.preventDefault();
    this.loadData();
  }

  changeRowsPerPage(n: number): void {
    this.rowsPerPage = n;
    this.loadData();
  }

}
