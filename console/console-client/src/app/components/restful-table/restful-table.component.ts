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
  @Input() multisortAllowed?: boolean;

  rows: string[][] = [];

  pageNumber: number = 0;
  pageSize: number = 25;
  totalPages: number = 0;
  totalElements: number = 0;

  pagination: number[] = [];

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
      column.sortDir = null;

      for (let sort of tableData.sort) {
        if (sort.column == column.id) {
          column.sortDir = sort.direction;
          break;
        }
      }
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

    this.pageNumber = tableData.pageNumber;
    this.pageSize = tableData.pageSize;
    this.totalElements = tableData.totalElements;
    this.totalPages = tableData.pageSize > 0 ? Math.ceil(tableData.totalElements / tableData.pageSize) : 0;

    let paginationFrom = tableData.pageNumber < 3 ? 1 : tableData.pageNumber - 2;
    let paginationTo = paginationFrom + 4;

    if (paginationTo > this.totalPages) {
      paginationTo = this.totalPages;
      console.log(paginationFrom + " - " + paginationTo);
      paginationFrom = paginationTo < 5 ? 1 : paginationTo - 4;
    }

    this.pagination = [];
    for (let p = paginationFrom; p <= paginationTo; p++) {
      this.pagination.push(p);
    }

    console.log(paginationFrom);
    console.log(paginationTo);
    console.log(this.pagination);

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

  changePage(page: number): void {
    console.log('change page: ' + page);
    this.loadData();
  }

  changePageSize(n: number): void {
  //  this.pageSize = n;
    this.loadData();
  }

}
