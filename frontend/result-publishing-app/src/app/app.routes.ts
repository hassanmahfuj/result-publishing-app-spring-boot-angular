import { Routes } from '@angular/router';
import { StudentComponent } from './components/student/student.component';
import { AdminComponent } from './components/admin/admin.component';
import { GetResultComponent } from './components/get-result/get-result.component';
import { ResultComponent } from './components/result/result.component';
import { StudentEditComponent } from './components/student-edit/student-edit.component';
import { ResultEditComponent } from './components/result-edit/result-edit.component';

export const routes: Routes = [
  {
    path: '',
    component: GetResultComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: '', redirectTo: 'student', pathMatch: 'full' },
      { path: 'student', component: StudentComponent },
      { path: 'student/create', component: StudentEditComponent },
      { path: 'student/edit/:id', component: StudentEditComponent },
      { path: 'result', component: ResultComponent },
      { path: 'result/create', component: ResultEditComponent },
      { path: 'result/edit/:id', component: ResultEditComponent },
    ],
  },
];
