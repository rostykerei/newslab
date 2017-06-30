import {Component, Input, OnInit} from "@angular/core";
import {TableDataService} from "./table-data.service";
import {TableData} from "./table-data";
import {Column} from "./column";
import {Sort} from "./sort";

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

  @Input() defaultPageSize?: 10 |25 | 50 | 100 | 200;
  @Input() defaultSort?: Sort[];

  rows: string[][] = [];

  pageNumber: number = 1;
  pageSize: number = 25;

  totalPages: number = 0;
  totalElements: number = 0;

  sort: Sort[] = [];

  pagination: number[] = [];

  ROWS_PER_PAGE_MENU: number[] = [10, 25, 50, 100, 200];

  constructor(private tableDataService: TableDataService) {

  }

  ngOnInit(): void {
    this.loadData(1, this.defaultPageSize || this.pageSize, this.defaultSort || this.sort);
  }

  loadData(pageNumber: number = this.pageNumber, pageSize: number = this.pageSize, sort: Sort[] = this.sort): void {
    this.loading = true;

    this.tableDataService.getData(pageNumber, pageSize, sort).then(
      data => this.dataLoaded(data)
    );
  }

  dataLoaded(data:TableData): void {
    this.tableData = data;
    this.init = true;
    this.loading = false;
  }

  set tableData(tableData: TableData) {
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

    this.sort = tableData.sort;
    this.pageNumber = tableData.pageNumber;
    this.pageSize = tableData.pageSize;
    this.totalElements = tableData.totalElements;
    this.totalPages = tableData.pageSize > 0 ? Math.ceil(tableData.totalElements / tableData.pageSize) : 0;

    let paginationFrom = tableData.pageNumber < 3 ? 1 : tableData.pageNumber - 2;
    let paginationTo = paginationFrom + 4;

    if (paginationTo > this.totalPages) {
      paginationTo = this.totalPages;
      paginationFrom = paginationTo < 5 ? 1 : paginationTo - 4;
    }

    this.pagination = [];
    for (let p = paginationFrom; p <= paginationTo; p++) {
      this.pagination.push(p);
    }
  }

  getColumnSort(column: Column): 'ASC' | 'DESC' | 'NONE' {
    for (let sort of this.sort){
      if (sort.column == column.id) {
        return sort.direction;
      }
    }

    return 'NONE';
  }

  changeSort(column: Column): void {
    if (column.sortable) {
      this.loadData(1, this.pageSize, [
        {
          column: column.id,
          direction: this.getColumnSort(column) == 'ASC' ? 'DESC' : 'ASC'
        }
      ]);
    }
  }

  refresh(e: Event): void {
    e.preventDefault();
    this.loadData();
  }

  changePage(page: number): void {
    this.loadData(page);
  }

  changePageSize(n: number): void {
    this.loadData(1, n);
  }

}
