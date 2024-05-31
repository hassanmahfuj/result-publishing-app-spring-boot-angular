import { Subject } from './subject';

export interface Marksheet {
  examination: string;
  year: number;
  rollNumber: number;
  groupName: string;
  gpa: number;
  grade: string;
  result: string;
  name: string;
  fatherName: string;
  motherName: string;
  datOfBirth: string;
  subjects: Subject[];
}
