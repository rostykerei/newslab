import {Component} from "@angular/core";
import {Column} from "../../components/restful-table/column";
import {Sort} from "../../components/restful-table/sort";

@Component({
  selector: 'nl-publishers-component',
  templateUrl: 'publishers.template.html'
})
export class PublishersComponent {

  tableColumns: Column[] = [
    { id: 'id', title: 'ID', sortable: true},
    { id: 'canonicalName', title: 'Canonical name', sortable: true },
    { id: 'title', title: 'Title', sortable: true },
    { id: 'url', title: 'URL', sortable: true},
    { id: '$actions', title: 'Actions', sortable: false }
  ];

  defaultSort: Sort[] = [
    {
      column: 'id',
      direction: 'ASC'
    }
  ];

}
