/** Name: Toan Nguyen
 * Assignment 5
 * All the element about the course
 */
 
public class Course extends BaseElement {
	private String CourseName;
	private CourseNum CourseNum;
	private CRN CRN;
	private Credit Credit;
	private Section Section;
	private Starttime Starttime;
	private Endtime Endtime;
	private Room Room;
	private Professor Professor;
	
	public CourseNum getCourseNum()
	{
		return CourseNum;
	}
	public CRN getCRN()
	{
		return CRN;
	}
	public Credit getCredit()
	{
		return Credit;
	}
	public Section getSection()
	{
		return Section;
	}
	public Starttime getStarttime()
	{
		return Starttime;
	}
	public Endtime getEndtime()
	{
		return Endtime;
	}
	public Room getRoom()
	{
		return Room;
	}
	public Professor getProfessor()
	{
		return Professor;
	}
	public String getName()
	{
		return CourseName;
	}
	public void setCourseName(String CourseName)
	{
		this.CourseName = CourseName;
	}
	public void setCourseNum(CourseNum CourseNum)
	{
		this.CourseNum = CourseNum;
	}
	public void setCRN (CRN CRN)
	{
		this.CRN = CRN;
	}
	public void setCredit(Credit Credit)
	{
		this.Credit = Credit;
	}
	public void setSection(Section Section)
	{
		this.Section = Section;
	}
	public void setStarttime(Starttime Starttime)
	{
		this.Starttime = Starttime;
	}
	public void setEndtime(Endtime Endtime)
	{
		this.Endtime = Endtime;
	}
	public void setRoom(Room Room)
	{
		this.Room = Room;
	}
	public void setProfessor(Professor Professor)
	{
		this.Professor = Professor;
	}
	public void setAttributeValue(String name, String value)
	{
		if (name.equals("CourseName"))
		{
			setCourseName(value);
		}
	}
	public String toString()
	{
		return "(Course Name: " + CourseName + ", CourseNum:" + CourseNum
				+ ", CRN:" + CRN
				+ ",Credit:" + Credit 
				+ ",Section:" + Section
				+ ",Start time:" + Starttime
				+ ",End time:" + Endtime
				+ ",Room:" + Room
				+ ",Professor:" + Professor
				+ ")";
	}
	
	
	
}
