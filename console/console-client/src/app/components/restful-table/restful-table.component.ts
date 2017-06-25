import {Component, Input} from "@angular/core";
import {ColumnDefinition} from "./column-definition";

@Component({
  selector: 'nl-restful-table',
  templateUrl: 'restful-table.template.html'
})
export class RestfulTableComponent {

  @Input() title: String;

  @Input() columnDefinitions: ColumnDefinition[];

  refresh(e: Event): void {
    e.preventDefault();
    alert('111');
  }

}
