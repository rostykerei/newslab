import {Component} from "@angular/core";
import {ColumnDefinition} from "../../components/restful-table/column-definition";

@Component({
  selector: 'nl-publishers-component',
  templateUrl: 'publishers.template.html'
})
export class PublishersComponent {

  tableColumns: ColumnDefinition[] = [
    { name: 'renderEngine', title: 'Rendering engile' },
    { name: 'browser', title: 'Browser' },
    { name: 'platforms', title: 'Platform(s)' },
    { name: 'engineVersion'},
    { name: 'cssGrade', title: 'CSS grade' }
  ];

}
