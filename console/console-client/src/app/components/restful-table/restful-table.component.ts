import {Component, Input, OnInit} from "@angular/core";
import {ColumnDefinition} from "./column-definition";

@Component({
  selector: 'nl-restful-table',
  templateUrl: 'restful-table.template.html'
})
export class RestfulTableComponent implements OnInit {

  @Input() title: String;

  @Input() columnDefinitions: ColumnDefinition[];

  isLoading: boolean = false;

  headers: any[] = [];

  ngOnInit(): void {
    for (let col of this.columnDefinitions) {
      this.headers.push({
        'title': col.title || col.name,
        'css': col.sortable ? 'sorting' : ''
      });
    }
  }

  refresh(e: Event): void {
    e.preventDefault();
    this.isLoading = !this.isLoading;
  }

}
