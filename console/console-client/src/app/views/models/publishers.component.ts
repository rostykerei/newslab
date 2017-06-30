import {Component} from "@angular/core";
import {PublishersTableActionsRenderer} from "./publishers-table-actions-renderer.component";
import {RestfulTableColumn} from "../../components/restful-table/types/restful-table-column.type";
import {RestfulTableSort} from "../../components/restful-table/types/restful-table-sort.type";

@Component({
  selector: 'nl-publishers-component',
  templateUrl: 'publishers.template.html'
})
export class PublishersComponent {

  tableColumns: RestfulTableColumn[] = [
    { id: 'id', title: 'ID', sortable: true},
    { id: 'canonicalName', title: 'Canonical name', sortable: true },
    { id: 'title', title: 'Title', sortable: true },
    { id: 'url', title: 'URL', sortable: true},
    { id: '$actions', title: 'Actions', sortable: false, renderer: PublishersTableActionsRenderer }
  ];

  defaultSort: RestfulTableSort[] = [
    {
      column: 'id',
      direction: 'ASC'
    }
  ];

}
